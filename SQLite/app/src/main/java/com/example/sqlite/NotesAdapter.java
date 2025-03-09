package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NotesAdapter extends BaseAdapter {
    //Khai báo biến toàn cục
    private MainActivity context;
    private int layout;
    private List<NotesModel> noteList;
    //tạo constructor
    public NotesAdapter(MainActivity context, int layout, List<NotesModel> noteList) {
        this.context = context;
        this.layout = layout;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //tạo viewHolder
    private class ViewHolder{
        TextView textViewNote;
        ImageView imageViewEdit ;
        ImageView imageViewDelete;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //gọi viewHolder
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.textViewNote = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.imageViewDelete= (ImageView) convertView.findViewById(R.id.btnDelete);
            viewHolder.imageViewEdit = (ImageView) convertView.findViewById(R.id.btnEdit);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final NotesModel notes = noteList.get(position);
        viewHolder.textViewNote.setText(notes.getNameNote());

        viewHolder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Cập nhật " + notes.getNameNote(), Toast.LENGTH_SHORT).show();
                //gọi Dialog trong MainActivity.java
                context.DialogCapNhatNotes(notes.getNameNote(),notes.getIdNote());
            }
        });

        //bắt sự kiện xóa notes
        viewHolder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogDelete(notes.getNameNote(), notes.getIdNote());
            }
        });

        return convertView;
    }

}
