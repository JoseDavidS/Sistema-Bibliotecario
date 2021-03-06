package com.SIGLO21.sistemabiblioteca;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author raulc
 */
public class MySQLConnection {
    
    public MySQLConnection(String _database, String _user, String _key){
        try{
            database = _database;
            user = _user;
            key = _key;
        }
        catch (Exception e){
            showMessageDialog(null, "Couldn't create database" + _database);
        }
        
    }
    private Connection connection;
    private Statement statement;
    private String database;
    private String user;
    private String key;
        
    
    public void create(String[] _attributes, String[] _values){
        if(_values.length == _attributes.length){
            try{
                this.connection = DriverManager.getConnection(database, user, key);
                StringBuilder query = new StringBuilder();
                query.append("create ");
                query.append(database);
                query.append(" (");

                for(int attribute = 0; attribute < _attributes.length; attribute++){
                    query.append(_attributes[attribute]);
                    query.append(",");
                    if (attribute == (_attributes.length - 1)){
                        query.append(")");
                    }
                }
                for(int value = 0; value < _values.length; value++){
                    query.append(" '");
                    query.append(_values[value]);
                    query.append("'");
                    if (value != (_values.length - 1)){
                        query.append(", ");
                    }
                }
                this.statement = this.connection.createStatement();
                this.statement.executeQuery(query.toString());
                this.statement.close();
                this.connection.close();
            }
            catch(SQLException e){
                showMessageDialog(null, "Couldn't connect to database" + this.database);
            }
        }
        else{
            showMessageDialog(null, "Implementation error");
        }
    }

    
    public void update(String[] _attributes, String[] _values, String _table,String[] _ref_attribute){
        if(_values.length == _attributes.length){
            try{
                this.connection = DriverManager.getConnection(database, user, key);
                StringBuilder query = new StringBuilder();
                query.append("update ");
                query.append(database);
                query.append(".");
                query.append(_table);
                query.append(" set ");

                for(int attribute = 0; attribute < _attributes.length; attribute++){
                    query.append(_attributes[attribute]);
                    query.append(",");
                    if (attribute == (_attributes.length - 1)){
                        query.append("where ");
                        query.append(_ref_attribute[0]);
                        query.append(" = ");
                        query.append(_ref_attribute[1]);
                    }
                }
                for(int value = 0; value < _values.length; value++){
                    query.append(" '");
                    query.append(_values[value]);
                    query.append("'");
                    if (value != (_values.length - 1)){
                        query.append(", ");
                    }
                
                }
                this.statement = connection.createStatement();
                this.statement.executeQuery(query.toString());
                this.statement.close();
                this.connection.close();
            }
            catch(SQLException e){
                showMessageDialog(null, "Couldn't connect to database" + this.database);
            }
            
        }
        else{
            showMessageDialog(null, "Implementation error");
        }
    }
    
    
}
