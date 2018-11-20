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


    private void adicionaConsumo(int position) {
        ItemComandinha item = this.dataSet.get(position);
        item.setQuantidade(item.getQuantidade() + 1);
        notifyDataSetChanged();
    }

    private void removeConsumo(int position) {
        ItemComandinha item = this.dataSet.get(position);
        if(item.getQuantidade() >= 1) {
            item.setQuantidade(item.getQuantidade() - 1);
            notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "Vai devegar na cerveja amigo(a)", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        Log.i(ItemComandinha.class.getName(), "Clicou aqui");
        View parent = (View)v.getParent();
        Toast.makeText(this.mContext, String.valueOf(parent.getTag()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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
        viewHolder.itemComandaMaisUmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionaConsumo(position);
            }
        });
        viewHolder.itemComandaTiraUmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeConsumo(position);
            }
        });

        return convertView;
    }
}
