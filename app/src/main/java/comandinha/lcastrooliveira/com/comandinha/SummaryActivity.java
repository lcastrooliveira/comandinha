package comandinha.lcastrooliveira.com.comandinha;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SummaryActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private int total;
    private ListView summaryListView;
    private ArrayAdapter<String> adapter;
    private TextView totalItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Intent intent = getIntent();
        items = intent.getStringArrayListExtra("ITEMS");
        //if(items == null) items = new String[0];
        total = intent.getIntExtra("TOTAL", 0);
        popularLista();
    }

    private void popularLista() {
        summaryListView = findViewById(R.id.tabSummaryListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        View footerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_summary_footer, null, false);
        summaryListView.addFooterView(footerView);
        summaryListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        totalItems = findViewById(R.id.totalItems);
        totalItems.setText(String.valueOf(total));
    }
}
