package com.example.alayesanmifemi.mlforensic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alayesanmi Femi on 11/05/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewHolder>{
    private Context mContext;
    private List<Cases> mData;

    public RecyclerAdapter(Context mContext, List<Cases> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater Inflatter = LayoutInflater.from(mContext);
        view = Inflatter.inflate(R.layout.cardview_case_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {
        holder.case_title_text.setText(mData.get(position).getTitle());
        //holder.case_img_thumbnail.setImageResource(mData.get(position).id);
        holder.case_item_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CaseDetail_Activity.class);
                intent.putExtra("Title", mData.get(position).getTitle());
//              intent.putExtra("Detective", mData.get(position).getDetective());
                intent.putExtra("Location", mData.get(position).getLocation());
                intent.putExtra("Weather", mData.get(position).getWeather());
                intent.putExtra("Description", mData.get(position).getDescription());
                intent.putExtra("Category", mData.get(position).getCrimeType());
                intent.putExtra("Arrival Time", mData.get(position).getArrivalTimeDate());
                intent.putExtra("Departure Time", mData.get(position).getDepartureTimeDate());
                //intent.putExtra("Thumbnail", mData.get(position).getImg_thumbnail());
                mContext.startActivity(intent);
            }
        });

    }@Override
    public int getItemCount() {
        return mData.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        TextView case_title_text;
        //ImageView case_img_thumbnail;
        CardView case_item_cardview;
        public myViewHolder(View itemView){
            super(itemView);
            case_title_text = (TextView) itemView.findViewById(R.id.case_title_id);
          //  case_img_thumbnail = (ImageView) itemView.findViewById(R.id.case_img_id);
            //case_img_thumbnail.setImageResource(R.drawable.crime);
            case_item_cardview = (CardView) itemView.findViewById(R.id.case_item_id);
        }
    }
}
