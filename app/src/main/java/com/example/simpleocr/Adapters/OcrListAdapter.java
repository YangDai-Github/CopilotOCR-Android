package com.example.simpleocr.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.simpleocr.Model.ItemClick;
import com.example.simpleocr.Model.OcrItem;
import com.example.simpleocr.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class OcrListAdapter extends RecyclerView.Adapter<OcrListAdapter.ItemViewHolder> {
    private final List<OcrItem> list;
    ItemClick itemClick;

    public OcrListAdapter(List<OcrItem> list, ItemClick itemClick) {
        this.list = list;
        this.itemClick = itemClick;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ItemViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        addAnimation(holder);
    }

    private void addAnimation(ItemViewHolder holder) {
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.item_anim));
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        view.setOnClickListener(v -> {
            int position1 = itemViewHolder.getAdapterPosition();
            ImageView imageView = itemViewHolder.imageView;
            imageView.setTransitionName("testImg");
            itemClick.onClick(list.get(position1), position1, imageView);
        });

        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        holder.textViewText.setText(list.get(position).getText());
        holder.textViewDate.setText(list.get(position).getDate());
        String image = list.get(holder.getAdapterPosition()).getImage();
        if (!image.isEmpty()) {
            Glide.with(holder.itemView.getContext()).asBitmap().load(image).sizeMultiplier(0.8f).into(holder.imageView);
        } else {
            Glide.with(holder.itemView.getContext()).clear(holder.imageView);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textViewText, textViewDate;
        ShapeableImageView imageView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_container);
            textViewText = itemView.findViewById(R.id.textview_text);
            textViewDate = itemView.findViewById(R.id.textview_date);
            imageView = itemView.findViewById(R.id.imageShow);
        }
    }
}
