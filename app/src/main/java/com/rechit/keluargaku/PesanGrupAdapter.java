package com.rechit.keluargaku;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.rechit.keluargaku.Model.Pesan;
import com.rechit.keluargaku.Model.PesanGrup;

import org.w3c.dom.Text;

import java.util.List;

public class PesanGrupAdapter extends RecyclerView.Adapter<PesanGrupAdapter.PesanGrupAdapterViewHolder>
{

    Context mContext;
    List<PesanGrup> mPesan;
    DatabaseReference reference;


    public PesanGrupAdapter(Context mContext, List<PesanGrup> mPesan, DatabaseReference reference){
        this.mContext = mContext;
        this.reference = reference;
        this.mPesan = mPesan;

    }


    @Override
    public PesanGrupAdapterViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pesan_grup,parent,false);
        return new PesanGrupAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PesanGrupAdapterViewHolder holder, int position) {
        PesanGrup pesan = mPesan.get(position);

//        holder.show_message.setText(chat.getMessage());

//        if(imageurl.equals("default")){
//            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
//        } else{
//            Glide.with(mContext).load(imageurl).into(holder.profile_image);
//        }
//        if (position == mPesan.size()-1){
//            if(chat.isIsseen()){
//                holder.txt_seen.setText("Seen");
//            } else{
//                holder.txt_seen.setText("Delivered");
//            }
//        } else{
//            holder.txt_seen.setVisibility(View.GONE);
//        }
        if(pesan.getName().equals(AllMethods.name))
        {
            holder.tvTitle.setText("You: "+pesan.getMessage());
            holder.tvTitle.setGravity(Gravity.START);
            holder.ll.setBackgroundColor(Color.parseColor("#EF9E73"));
        }else{
            holder.tvTitle.setText(pesan.getName() + ":" + pesan.getMessage());
            holder.ibDelete.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return mPesan.size();
    }

    public class PesanGrupAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageButton ibDelete;
        LinearLayout ll;
        public PesanGrupAdapterViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ibDelete = itemView.findViewById(R.id.ibDelete);
            ll = (LinearLayout)itemView.findViewById(R.id.llMessage);

            ibDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reference.child(mPesan.get(getAdapterPosition()).getKey()).removeValue();
                }
            });
        }




    }

}
