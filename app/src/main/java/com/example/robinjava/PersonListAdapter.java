package com.example.robinjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonViewHolder> {

    private List<Person> mPersonList;

    public PersonListAdapter(List<Person> personList) {
        mPersonList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_get_all_list, parent, false);
        return new PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = mPersonList.get(position);
        holder.navnTextView.setText(person.getNavn());
        holder.adresseTextView.setText(String.valueOf(person.getAdresse()));
        holder.nationalitetTextView.setText(String.valueOf(person.getNationalitet()));
        holder.tlfTextView.setText(String.valueOf(person.getNationalitet()));
        holder.interesse1TextView.setText(String.valueOf(person.getNationalitet()));
        holder.interesse2TextView.setText(String.valueOf(person.getNationalitet()));
        holder.interesse3TextView.setText(String.valueOf(person.getNationalitet()));
    }

    @Override
    public int getItemCount() {
        return mPersonList.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView navnTextView;
        TextView adresseTextView;
        TextView nationalitetTextView;
        TextView tlfTextView;
        TextView interesse1TextView;
        TextView interesse2TextView;
        TextView interesse3TextView;

        PersonViewHolder(View itemView) {
            super(itemView);
            navnTextView = itemView.findViewById(R.id.navnTextView);
            adresseTextView = itemView.findViewById(R.id.adresseTextView);
            nationalitetTextView = itemView.findViewById(R.id.nationalitetTextView);
            tlfTextView = itemView.findViewById(R.id.tlfTextView);
            interesse1TextView = itemView.findViewById(R.id.interesse1TextView);
            interesse2TextView = itemView.findViewById(R.id.interesse2TextView);
            interesse3TextView = itemView.findViewById(R.id.interesse3TextView);
        }
    }
}
