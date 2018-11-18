package comandinha.lcastrooliveira.com.comandinha.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import comandinha.lcastrooliveira.com.comandinha.R;
import comandinha.lcastrooliveira.com.comandinha.model.ItemComandinha;

public class ItemComandinhaAdapter extends ArrayAdapter<ItemComandinha> implements View.OnClickListener{

    private ArrayList<ItemComandinha> dataSet;
    Context mContext;


    public ItemComandinhaAdapter(ArrayList<ItemComandinha> data, Context context) {
        super(context, R.layout.item_comanda, data);
        this.dataSet = data;
        this.mContext=context;
    }


    @Override
    public void onClick(View v) {

    }
}
