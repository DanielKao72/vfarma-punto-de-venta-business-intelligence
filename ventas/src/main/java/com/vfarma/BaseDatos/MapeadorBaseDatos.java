package com.vfarma.BaseDatos;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MapeadorBaseDatos {
    void mapearDelConjuntoResultado(ResultSet conjuntoResultado) throws SQLException;
}
