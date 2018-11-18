package comandinha.lcastrooliveira.com.comandinha.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import comandinha.lcastrooliveira.com.comandinha.R;
import comandinha.lcastrooliveira.com.comandinha.model.ItemComandinha;

public class ItemComandinhaAdapter extends ArrayAdapter<ItemComandinha> implements View.OnClickListener{

    private List<ItemComandinha> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView itemComandaNomeTextView;
        TextView itemComandaQuantidadeTextView;
        ImageButton itemComandaMaisUmButton;
        ImageButton itemComandaTiraUmButton;
    }

    public ItemComandinhaAdapter(List<ItemComandinha> data, Context context) {
        super(context, R.layout.item_comanda, data);
        this.dataSet = data;
        this.mContext=context;
    }


    @Override
    public void onClick(View v) {
        Log.i(ItemComandinha.class.getName(), "Clicou aqui");
        Toast.makeText(this.mContext, "Lucas", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemComandinha itemComandinha = getItem(position);

        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_comanda, parent, false);
            viewHolder.itemComandaNomeTextView = convertView.findViewById(R.id.itemComandaNomeTextView);
            viewHolder.itemComandaQuantidadeTextView = convertView.findViewById(R.id.itemComandaQuantidadeTextView);
            viewHolder.itemComandaMaisUmButton = convertView.findViewById(R.id.itemComandaMaisUmButton);
            viewHolder.itemComandaTiraUmButton = convertView.findViewById(R.id.itemComandaTiraUmButton);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.itemComandaNomeTextView.setText(itemComandinha.getNome());
        viewHolder.itemComandaQuantidadeTextView.setText(String.valueOf(itemComandinha.getQuantidade()));
        viewHolder.itemComandaMaisUmButton.setOnClickListener(this);
        viewHolder.itemComandaTiraUmButton.setOnClickListener(this);

        return convertView;
    }
}
