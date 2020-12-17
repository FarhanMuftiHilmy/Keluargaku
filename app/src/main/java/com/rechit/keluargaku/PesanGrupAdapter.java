package com.rechit.keluargaku;

import android.content.ClipData;
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

public class PesanGrupAdapter extends RecyclerView.Adapter<PesanGrupAdapter.ViewHolder>
{
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    Context mContext;
    List<PesanGrup> mPesan;
    DatabaseReference reference;

    FirebaseUser fuser;


    public PesanGrupAdapter(Context mContext, List<PesanGrup> mPesan, DatabaseReference reference){
        this.mContext = mContext;
        this.reference = reference;
        this.mPesan = mPesan;
    }


    @Override
    public PesanGrupAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT){
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);

            return new PesanGrupAdapter.ViewHolder(view);
        } else{
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_group_left, parent, false);

            return new PesanGrupAdapter.ViewHolder(view);
        }
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pesan_grup,parent,false);
//        return new PesanGrupAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PesanGrup pesan = mPesan.get(position);





//        if(imageurl.equals("default")){
//            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
//        } else{
//            Glide.with(mContext).load(imageurl).into(holder.profile_image);
//        }
        if (position == mPesan.size()-1){
            if(pesan.Isseen()){
                holder.txt_seen.setText("Seen");
            } else{
                holder.txt_seen.setText("Delivered");
            }
        } else{
            holder.txt_seen.setVisibility(View.GONE);
        }
        if(pesan.getName().equals(AllMethods.name))
        {
            holder.show_message.setText(pesan.getMessage());
            //holder.ll.setBackgroundResource(R.drawable.background_right);
        }else{
            holder.show_message.setText(pesan.getMessage());
            holder.username.setText(pesan.getName());
        }

//        if(pesan.getName().equals(AllMethods.name))
//        {
//            holder.tvTitle.setText("You: "+pesan.getMessage());
//            holder.tvTitle.setGravity(Gravity.START);
//            holder.ll.setBackgroundColor(Color.parseColor("#EF9E73"));
//            //holder.ll.setBackgroundResource(R.drawable.background_right);
//        }else{
//            holder.tvTitle.setText(pesan.getName() + ":" + pesan.getMessage());
//            holder.ibDelete.setVisibility(View.GONE);
//        }


    }

    @Override
    public int getItemCount() {
        return mPesan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView tvTitle;
//        ImageButton ibDelete;
//        LinearLayout ll;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            tvTitle = itemView.findViewById(R.id.tvTitle);
//            ibDelete = itemView.findViewById(R.id.ibDelete);
//            ll = (LinearLayout)itemView.findViewById(R.id.llMessage);
//
//            ibDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    reference.child(mPesan.get(getAdapterPosition()).getKey()).removeValue();
//                }
//            });
//        }
            public TextView show_message;
            public ImageView profile_image;

            public TextView txt_seen;

            public TextView username;

            public ViewHolder(View itemView){
                super(itemView);

                show_message = itemView.findViewById(R.id.show_message);
                profile_image = itemView.findViewById(R.id.profile_image);
                txt_seen = itemView.findViewById(R.id.txt_seen);
                username = itemView.findViewById(R.id.username);
            }
    }

    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (mPesan.get(position).getName().equals(AllMethods.name)){
            return MSG_TYPE_RIGHT;
        } else{
            return MSG_TYPE_LEFT;
        }
    }


}
