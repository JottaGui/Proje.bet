package com.example.projebet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projebet.model.Slot;

public class MainActivity extends AppCompatActivity {

        private Button btnJogar;
        private TextView txtResultado;
        private ImageView img01, img02, img03, img04, img05, img06, img07, img08, img09;
        private Slot game;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            btnJogar = findViewById(R.id.jogar);
            txtResultado = findViewById(R.id.Resultado);
            img01 = findViewById(R.id.imageView1);
            img02 = findViewById(R.id.imageView2);
            img03 = findViewById(R.id.imageView3);
            img04 = findViewById(R.id.imageView4);
            img05 = findViewById(R.id.imageView5);
            img06 = findViewById(R.id.imageView6);
            img07 = findViewById(R.id.imageView7);
            img08 = findViewById(R.id.imageView8);
            img09 = findViewById(R.id.imageView9);

            game = new Slot();

            btnJogar.setOnClickListener(v -> {

                Slot.RoundResult result = game.play();
                int[][] grid = result.getGrid();

                img01.setImageResource(getImagem(grid[0][0]));
                img02.setImageResource(getImagem(grid[0][1]));
                img03.setImageResource(getImagem(grid[0][2]));

                img04.setImageResource(getImagem(grid[1][0]));
                img05.setImageResource(getImagem(grid[1][1]));
                img06.setImageResource(getImagem(grid[1][2]));

                img07.setImageResource(getImagem(grid[2][0]));
                img08.setImageResource(getImagem(grid[2][1]));
                img09.setImageResource(getImagem(grid[2][2]));

                txtResultado.setText(result.getMessage());
            });
        }

        private int getImagem(int valor) {
            switch (valor) {
                case 0:
                    return R.drawable.simbolo_0;
                case 1:
                    return R.drawable.simbolo_1;
                case 2:
                    return R.drawable.simbolo_2;
                case 3:
                    return R.drawable.simbolo_3;
                default:
                    return R.drawable.simbolo_0;
            }
        }
    }
