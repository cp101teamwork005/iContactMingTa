package com.example.mingtayang.icontact;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentExamFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<AssignDate> dates;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_exam, container, false); //回傳父元件(linearLayout) 最尾要記得加false否則預設為true

        getActivity().setTitle(R.string.title_exam);

        findViews(view);

        //rv
        dates = getTheDates();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new StudentExamFragment.ExamAdapter(dates, getActivity()));

        return view; //要改成回傳view
    }

    //rv假資料
    private List<AssignDate> getTheDates() {
        List<AssignDate> dates = new ArrayList<>();
        dates.add(new AssignDate(107, 4, 10));
        dates.add(new AssignDate(107, 4, 6));
        dates.add(new AssignDate(107, 4, 3));

        return dates;
    }

    private void findViews(View view) {
        recyclerView = view.findViewById(R.id.rvExam);

    }


    //rv
    private class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {
        private List<AssignDate> dates;
        private Context context;

        public ExamAdapter(List<AssignDate> dates, Context context) {
            this.dates = dates;
            this.context = context;
        }

        @NonNull
        @Override
        public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View itemView = layoutInflater.inflate(R.layout.cardview_exam, parent, false);


            return new ExamViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ExamViewHolder examViewHolder, int position) {
            AssignDate assignDate = dates.get(position);
            examViewHolder.textView.setText(assignDate.showDate());
            examViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Call Detail!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return dates.size();
        }

        class ExamViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;


            ExamViewHolder(View itemView) {
                super(itemView);
                this.textView = itemView.findViewById(R.id.tvDate);

            }
        }
    }
}
