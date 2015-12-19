package cl.telematica.android.certamen2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by franciscocabezas on 11/20/15.
 */
public class UIAdapter extends RecyclerView.Adapter<UIAdapter.ViewHolder> {
    private List<Libro> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public TextView mTextView2;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.ID);
            mTextView2 = (TextView) v.findViewById(R.id.Joke);
        }
    }

    public UIAdapter(List<Libro> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public UIAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Libro libro = mDataset.get(position);
        holder.mTextView.setText(libro.getID());
        holder.mTextView2.setText(libro.getJokes());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
