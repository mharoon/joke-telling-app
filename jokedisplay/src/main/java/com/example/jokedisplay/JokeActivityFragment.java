package com.example.jokedisplay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class JokeActivityFragment extends Fragment {


    public JokeActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_joke_activity, container, false);
        String joke = getActivity().getIntent().getStringExtra(JokeActivity.JOKE_KEY_EXTRA);

        TextView jokeTextView = (TextView) root.findViewById(R.id.joke_text);
        if (joke != null && joke.length() != 0) {
            jokeTextView.setText(joke);
        }

        return root;

    }

}
