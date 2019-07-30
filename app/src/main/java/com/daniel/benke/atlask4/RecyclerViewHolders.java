package com.daniel.benke.atlask4;

/**
 * Created by Andrea on 12/02/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView laminaName;
    public ImageView laminaPhoto;

   // private String text2;
    //private final Context context;


    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        laminaName = (TextView)itemView.findViewById(R.id.lamina_name);
        laminaPhoto = (ImageView)itemView.findViewById(R.id.lamina_photo);

  //      context = itemView.getContext();

    }

    @Override
    public void onClick(View view) {
      // text2 =this.laminaName.getText().toString();
        //Toast.makeText(view.getContext(), "Clicked Country Position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
       // Toast.makeText(view.getContext(), "Clicked Country Position = " + text2, Toast.LENGTH_SHORT).show();



    }
}