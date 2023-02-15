package com.example.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notifications.databinding.CustomUserItemBinding;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

   private ArrayList<User> users;
    private OnUserClickListener listener;

    public UserAdapter(ArrayList<User> users, OnUserClickListener listener) {
        this.users = users;
        this.listener = listener;
    }

    public OnUserClickListener getListener() {
        return listener;
    }

    public void setListener(OnUserClickListener listener) {
        this.listener = listener;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }



    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_user_item
        , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {

        User user = users.get(position);

        holder.bind(user);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserHolder extends RecyclerView.ViewHolder{

        private User user;
       CustomUserItemBinding binding;

        public UserHolder(@NonNull View itemView) {
            super(itemView);

            binding = CustomUserItemBinding.bind(itemView);

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onUserClicked(user);
                }
            });
        }

        public void bind(User user){

            this.user = user;
            binding.userIv.setImageResource(user.getImage());
            binding.userTvName.setText(user.getName());
            binding.userTvEmail.setText(user.getEmail());
            binding.userTvSalary.setText(String.valueOf(user.getSalary()));
        }


    }
    interface OnUserClickListener{

        void onUserClicked(User user);
    }
}
