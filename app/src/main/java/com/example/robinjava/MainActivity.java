package com.example.robinjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class MainActivity extends AppCompatActivity {

    TextView OutputTxt;
    EditText InputTxt;
    Button BtnToCreate;
    Button BtnGetAll;
    Button BtnGetById;
    Spinner NationalitySpinner;

    private static final String TAG = "MainActivity";
    private Connection connection = DatabaseConnection.getConnection();
    // Connection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputTxt = (EditText) findViewById(R.id.TxtInput);
        OutputTxt = (TextView) findViewById(R.id.TxtOutput);
        BtnToCreate = (Button) findViewById(R.id.BtnToCreate);
        BtnGetAll = (Button) findViewById(R.id.BtnGetAll);
        BtnGetById = (Button) findViewById(R.id.BtnGetById);
        NationalitySpinner = (Spinner) findViewById(R.id.nationalitySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NationalitySpinner.setAdapter(adapter);

        new ConnectToDatabaseTask().execute();
    }

    private class ConnectToDatabaseTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            // Attempt to establish the database connection
            connection = DatabaseConnection.getConnection();
            return connection != null;
        }


        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                // Connection successful, perform database operations here
                Log.d(TAG, "Connection successful");

                // CREATE
                try {
                    String sql = "INSERT INTO Personer (Navn, Adresse, Nationalitet, Favourite, Tlf, Interesse1, Interesse2, Interesse3) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, "value1");
                    statement.setString(2, "value2");
                    statement.setString(3, "value3");
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        Log.d(TAG, "En ny person er oprettet!");
                    }
                    else {
                        Log.d(TAG, "Ingen person oprettet!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // READ - GET ALL
                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM Personer");
                    while (resultSet.next()) {
                        // Do something with each row
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // READ - GET BY ID


                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM Personer where PersonID = 1");

                    if (resultSet.next()) {
                        String value = resultSet.getString("Navn");
                        Log.d(TAG, "Navn: " + value);
                        OutputTxt.setText(value);
                    }
                    //while (resultSet.next())
                    //{
                      //  OutputTxt.setText(resultSet.getString(columnIndex ));
                    //}
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // UPDATE
                try {
                    String sql = "UPDATE Personer SET column1 = ? WHERE column2 = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, "newvalue1");
                    statement.setString(2, "value2");
                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        Log.d(TAG, "Personen er opdateret.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // DELETE
                try {
                    String sql = "DELETE FROM Personer WHERE PersonID = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, "value1");
                    int rowsDeleted = statement.executeUpdate();
                    if (rowsDeleted > 0) {
                        Log.d(TAG, "Personen er slettet.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Don't forget to close the connection when you're done
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                // Connection failed, handle the error here
                Log.d(TAG, "Connection failed");
            }
        }
    }
}