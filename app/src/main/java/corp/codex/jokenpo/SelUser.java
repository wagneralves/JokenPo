package corp.codex.jokenpo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SelUser extends AppCompatActivity {

    ImageButton jogador1, jogador2;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_user);

        jogador1 = findViewById(R.id.jogador1);
        jogador2 = findViewById(R.id.jogador2);

        jogador1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(SelUser.this, JoaoActivity.class);
                startActivity(it);
            }
        });


        jogador2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(SelUser.this, MariaActivity.class);
                startActivity(it);

            }
        });
    }

}
