package net.etfbl.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

@Service
public class AdministratorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkIsAdministrator(String username) {
        return jdbcTemplate.execute(
                "{CALL checkIsAdministrator(?, ?)}",
                (CallableStatementCallback<Boolean>) cs -> {
                    cs.setString(1, username);
                    cs.registerOutParameter(2, Types.BOOLEAN);
                    cs.execute();
                    return cs.getBoolean(2);
                }
        );
    }
}