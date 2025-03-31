package com.example.bt08viewflippercricleindicator2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bt08viewflippercricleindicator2.model.ImagesSlider;

import java.util.List;

public class ImagesViewPager2Adapter extends RecyclerView.Adapter<ImagesViewPager2Adapter.ImagesViewHolder> {
    private List<ImagesSlider> imagesList;

    public ImagesViewPager2Adapter(List<ImagesSlider> imagesList) {
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_images, parent, false);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        // set or load image here
        ImagesSlider images = imagesList.get(position);
        if (images == null) {
            return;
        }
        Glide.with(holder.imageView.getContext())
                .load(images.getAvatar()) // Đảm bảo avatar là URL hợp lệ
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (imagesList == null)
            return 0;
        return imagesList.size();
    }

    public static class ImagesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView); // Change to the actual ID
        }
    }
}