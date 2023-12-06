package ru.netology.sql;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.netology.data.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL {
    private static QueryRunner runner = new QueryRunner();

    private SQL() {

    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    // аннотация SneakyTrows нужна для того, чтобы проверяемое исключение SQLException (строка 20) сделать непроверяемым и необрабатывать его!
    public static Data.VerificationCode getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var code = runner.query(conn, codeSQL, new ScalarHandler<String>());
        // ScalarHandler - это обработчик, возвращаемый одно значение <тип значения> !
        return new Data.VerificationCode(code);
        // VerificationCode - в классе Data имеется класс VerificationCode, который определяет структуру объекта, с кодом верификации !
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM auth_codes");
        runner.execute(connection, "DELETE FROM card_transactions");
        runner.execute(connection, "DELETE FROM cards");
        runner.execute(connection, "DELETE FROM users");
    }

    @SneakyThrows
    public static void cleanAuthCodes() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM auth_codes");
    }


}
