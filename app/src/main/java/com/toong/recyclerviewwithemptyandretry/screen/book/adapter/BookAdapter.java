package com.toong.recyclerviewwithemptyandretry.screen.book.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.toong.recyclerviewwithemptyandretry.R;
import com.toong.recyclerviewwithemptyandretry.base.BaseRecyclerViewAdapter;
import com.toong.recyclerviewwithemptyandretry.base.BaseViewHolder;

public class BookAdapter extends BaseRecyclerViewAdapter<BookItem> {

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return BookViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (holder instanceof BookViewHolder) {
            ((BookViewHolder) holder).bind(getItem(position));
        }
    }

    static class BookViewHolder extends BaseViewHolder<BookItem> implements View.OnClickListener {
        private TextView tvName;

        BookViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.text_name);
        }

        static BookViewHolder create(ViewGroup parent) {
            return new BookViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_book, parent, false));
        }

        @Override
        public void bind(BookItem item) {
            super.bind(item);
            tvName.setText(item.getName());
        }

        @Override
        public void onClick(View v) {

        }
    }
}
