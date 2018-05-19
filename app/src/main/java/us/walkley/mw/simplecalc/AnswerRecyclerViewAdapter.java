package us.walkley.mw.simplecalc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import us.walkley.mw.simplecalc.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class AnswerRecyclerViewAdapter extends RecyclerView.Adapter<AnswerRecyclerViewAdapter.ViewHolder> {

    private final List<AnswerItem> mValues;

    public AnswerRecyclerViewAdapter(List<AnswerItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.answer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mImgView.setImageResource(mValues.get(position).getImgSrc());
        holder.mTextView.setText(Double.toString(mValues.get(position).getAnswer()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImgView;
        public final TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImgView = (ImageView) view.findViewById(R.id.answer_ImageView);
            mTextView = (TextView) view.findViewById(R.id.answer_TextView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText() + "'";
        }
    }
}
