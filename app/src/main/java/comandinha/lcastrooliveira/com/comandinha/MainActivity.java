package comandinha.lcastrooliveira.com.comandinha;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comandinha.lcastrooliveira.com.comandinha.adapters.ItemComandinhaAdapter;
import comandinha.lcastrooliveira.com.comandinha.model.ItemComandinha;

public class MainActivity extends AppCompatActivity {

    private ListView listViewComandinha;
    private ItemComandinhaAdapter adapter;
    private FloatingActionButton pedirContaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewComandinha = findViewById(R.id.listViewComanda);
        pedirContaButton = findViewById(R.id.pedirContaButton);

        List<ItemComandinha> itemsComanda = new ArrayList<>();
        itemsComanda.add(new ItemComandinha("Chopp", 2));
        itemsComanda.add(new ItemComandinha("Porção", 1));

        adapter = new ItemComandinhaAdapter(itemsComanda, getApplicationContext());
        listViewComandinha.setAdapter(adapter);
        pedirContaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "A conta por favor!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
