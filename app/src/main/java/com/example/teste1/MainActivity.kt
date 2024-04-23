package com.example.teste1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.sql.DriverManager
import java.sql.SQLException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvTextoo : TextView = findViewById(R.id.Texto)
        val btnmuda : Button = findViewById(R.id.button)

        btnmuda.setOnClickListener { connectionwith(tvTextoo) }
    }

    private fun connectionwith(tvTexto : TextView){
        val jdbcUrl = "jdbc:mysql://tcc-sde.cxiky6ya09in.sa-east-1.rds.amazonaws.com:3306/tcc"
        val username = "tccsde"
        val password = "TccGrupo_2024"

        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(jdbcUrl, username, password)
            tvTexto.text = "siii"

            val statement = connection.createStatement()
            val sql = "SELECT * FROM Usuario"
            val resultSet = statement.executeQuery(sql)

            while (resultSet.next()) {
                val coluna1 = resultSet.getString("id")
                val coluna2 = resultSet.getString("p_nome")
                println("$coluna1 - $coluna2")
            }
            resultSet.close()
            statement.close()
            connection.close()
        } catch (e: SQLException) {
            tvTexto.text = e.toString()
            e.printStackTrace()
        }
    }

}