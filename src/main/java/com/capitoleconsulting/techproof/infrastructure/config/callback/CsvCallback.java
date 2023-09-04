package com.capitoleconsulting.techproof.infrastructure.config.callback;

import com.capitoleconsulting.techproof.infrastructure.config.callback.input.ProductInput;
import com.capitoleconsulting.techproof.infrastructure.config.callback.input.SizeInput;
import com.capitoleconsulting.techproof.infrastructure.config.callback.input.StockInput;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.capitoleconsulting.techproof.infrastructure.utils.JsonUtils.fromMap;

@Slf4j
public class CsvCallback implements Callback {

    private final ResourceLoader resourceLoader;

    public CsvCallback(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public String getCallbackName() {
        return "csv_callback";
    }

    @Override
    public boolean supports(Event event, Context context) {
        return event == Event.AFTER_MIGRATE;
    }

    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return true;
    }

    @Override
    public void handle(Event event, Context context) {
        try {
            Connection connection = context.getConnection();
            connection.setAutoCommit(false);
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(this.resourceLoader);
            if (findAll(connection, "product", ProductInput.class).isEmpty()) {
                processProducts(resolver.getResource("classpath:csv/product.csv"), connection);
            }
            if (findAll(connection, "size", SizeInput.class).isEmpty()) {
                processSizes(resolver.getResource("classpath:csv/size.csv"), connection);
            }
            if (findAll(connection, "stock", StockInput.class).isEmpty()) {
                processStocks(resolver.getResource("classpath:csv/stock.csv"), connection);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error processing CSV files", e);
        }
    }

    private void processProducts(Resource resource, Connection connection) throws IOException, SQLException {
        List<ProductInput> productInputList = readCsv(resource.getFile(), new String[] {"id", "sequence"}, ProductInput.class);
        log.info("Readed {} products from CSV.", productInputList.size());

        String sql = "INSERT INTO tech_proof.product (id, sequence) VALUES(?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (ProductInput productInput : productInputList) {
                statement.setLong(1, productInput.getId());
                statement.setInt(2, productInput.getSequence());
                statement.executeUpdate();
            }
        }
        connection.commit();
    }

    private void processSizes(Resource resource, Connection connection) throws IOException, SQLException {
        List<SizeInput> sizeInputList = readCsv(resource.getFile(), new String[] {"id", "productId", "backSoon", "special"}, SizeInput.class);
        log.info("Readed {} sizes from CSV.", sizeInputList.size());

        String sql = "INSERT INTO tech_proof.size (id, product_id, back_soon, special) VALUES(?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (SizeInput sizeInput : sizeInputList) {
                statement.setLong(1, sizeInput.getId());
                statement.setLong(2, sizeInput.getProductId());
                statement.setBoolean(3, sizeInput.getBackSoon());
                statement.setBoolean(4, sizeInput.getSpecial());
                statement.executeUpdate();
            }
        }
        connection.commit();
    }

    private void processStocks(Resource resource, Connection connection) throws IOException, SQLException {
        List<StockInput> stockInputList = readCsv(resource.getFile(), new String[] {"sizeId", "quantity"}, StockInput.class);
        log.info("Readed {} stocks from CSV.", stockInputList.size());

        String sql = "INSERT INTO tech_proof.stock (size_id, quantity) VALUES(?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (StockInput stockInput : stockInputList) {
                statement.setLong(1, stockInput.getSizeId());
                statement.setInt(2, stockInput.getQuantity());
                statement.executeUpdate();
            }
        }
        connection.commit();
    }

    private <T> List<T> findAll(Connection connection, String table, Class<T> cls) {
        String sql = String.format("SELECT * FROM tech_proof.%s", table);

        List<Map<String, Object>> mapList = new JdbcTemplate(new SingleConnectionDataSource(connection, true)).queryForList(sql);
        return mapList.stream()
                .map(mapObject -> fromMap(mapObject, cls))
                .toList();
    }

    public <T> List<T> readCsv(File file, String[] columns, Class<T> cls) throws IOException {
        final CsvMapper mapper = new CsvMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        try (MappingIterator<T> it = mapper.readerFor(cls)
                .with(mapper.schemaFor(cls)
                        .sortedBy(columns)
                        .withoutHeader()
                        .withColumnSeparator(',')
                        .withoutQuoteChar())
                .readValues(file)) {
            return it.readAll();
        }
    }
}
