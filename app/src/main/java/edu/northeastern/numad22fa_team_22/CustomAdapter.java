package edu.northeastern.numad22fa_team_22;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

// referenced the recyclerview documentation code
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private DatabaseReference mDatabase;
    ArrayList<Sticker> localDataSet;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView SenderTextView;
        private final TextView stickerTextView;
        private final TextView dateTextView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            SenderTextView = (TextView) view.findViewById(R.id.fromTextField);
            stickerTextView = (TextView) view.findViewById(R.id.stickerTextField);
            dateTextView = (TextView) view.findViewById(R.id.dateTextField);
        }

        public TextView getSenderTextView() {
            return SenderTextView;
        }
        public TextView getStickerTextView() {
            return stickerTextView;
        }
        public TextView getDateTextView() {
            return dateTextView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(ArrayList<Sticker> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Sticker currSticker = localDataSet.get(position);
        viewHolder.getSenderTextView().setText(currSticker.getStickerOwner());
        viewHolder.getStickerTextView().setText(currSticker.getStickerName());
        viewHolder.getDateTextView().setText(currSticker.getStickerSentDate());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
