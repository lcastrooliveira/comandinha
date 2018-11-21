package comandinha.lcastrooliveira.com.comandinha;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import comandinha.lcastrooliveira.com.comandinha.adapters.ItemComandinhaAdapter;
import comandinha.lcastrooliveira.com.comandinha.model.ItemComandinha;

public class MainActivity extends AppCompatActivity {

    private ListView listViewComandinha;
    private ItemComandinhaAdapter adapter;
    private FloatingActionButton pedirContaButton;
    private EditText editTextPetisco;
    private int posicaoAlteracao = -1;
    private  ArrayList<ItemComandinha> itemsComanda;
    private ImageButton persistirImageButton, cancelarImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewComandinha = findViewById(R.id.listViewComanda);
        pedirContaButton = findViewById(R.id.pedirContaButton);
        editTextPetisco = findViewById(R.id.editTextPetisco);
        persistirImageButton = findViewById(R.id.persistirImageButton);
        cancelarImageButton = findViewById(R.id.cancelImageButton);
        cancelarImageButton.setVisibility(View.INVISIBLE);

        popularLista();
        registerForContextMenu(listViewComandinha);

        pedirContaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SummaryActivity.class);
                ArrayList<String> items = new ArrayList<>();
                int total = 0;
                for(ItemComandinha ic : itemsComanda) {
                    items.add(ic.getNome()+"\t"+ic.getQuantidade());
                    total += ic.getQuantidade();
                }
                intent.putStringArrayListExtra("ITEMS", items);
                intent.putExtra("TOTAL", total);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.principal_menu_contexto, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info;
        info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menuItemAlterar:
                alterar(info.position);
                return true;
            case R.id.menuItemExcluir:
                excluir(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_menu_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemDetalhes:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuItemConfig:
                Toast.makeText(this, "hey", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void popularLista() {
        itemsComanda = new ArrayList<>();
        adapter = new ItemComandinhaAdapter(itemsComanda, getApplicationContext());
        listViewComandinha.setAdapter(adapter);
    }

    public void salvar(View view) {
        String petisco = editTextPetisco.getEditableText().toString();
        if(petisco.isEmpty()) {
            return;
        }

        if(posicaoAlteracao == -1) {
            itemsComanda.add(new ItemComandinha(petisco, 1));
        } else {
            ItemComandinha novoItemComandinha = new ItemComandinha(petisco, itemsComanda.get(posicaoAlteracao).getQuantidade());
            itemsComanda.remove(posicaoAlteracao);
            itemsComanda.add(posicaoAlteracao, novoItemComandinha);
            persistirImageButton.setImageResource(android.R.drawable.ic_input_add);
            cancelarImageButton.setVisibility(View.INVISIBLE);
            posicaoAlteracao = -1;
            listViewComandinha.setEnabled(true);
        }
        editTextPetisco.setText(null);
        Collections.sort(itemsComanda);
        adapter.notifyDataSetChanged();
    }

    public void cancelar(View view) {
        editTextPetisco.setText(null);
        persistirImageButton.setImageResource(android.R.drawable.ic_input_add);
        cancelarImageButton.setVisibility(View.INVISIBLE);
        listViewComandinha.setEnabled(true);
        posicaoAlteracao = -1;
    }

    private void alterar(int position) {
        ItemComandinha itemComandinha = itemsComanda.get(position);
        editTextPetisco.setText(itemComandinha.getNome());
        editTextPetisco.setSelection(editTextPetisco.getEditableText().toString().length());
        persistirImageButton.setImageResource(android.R.drawable.ic_menu_save);
        cancelarImageButton.setVisibility(View.VISIBLE);
        listViewComandinha.setEnabled(false);
        posicaoAlteracao = position;
    }

    private void excluir(int position) {
        itemsComanda.remove(position);
        adapter.notifyDataSetChanged();
    }
}
