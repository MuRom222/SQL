package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static QueryRunner runner = new QueryRunner();

    private SQLHelper(){
    }

    private static Connection getconn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    public static DataHelper.VerificationCode getVerificationCode(){
        var verificationCodeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1;";
        try (var conn = getconn()){
            var code = runner.query(conn, verificationCodeSQL, new ScalarHandler<String>());
            return new DataHelper.VerificationCode(code);
        } catch (SQLException exception){
            exception.printStackTrace();
        } return null;
    }

    @SneakyThrows
    public static void cleanDataBase(){
        var connection = getconn();
        runner.execute(connection, "DELETE FORM auth_code");
        runner.execute(connection, "DELETE FROM card_transactions");
        runner.execute(connection, "DELETE FORM cards");
        runner.execute(connection, "DELETE FORM users");

    }
}
