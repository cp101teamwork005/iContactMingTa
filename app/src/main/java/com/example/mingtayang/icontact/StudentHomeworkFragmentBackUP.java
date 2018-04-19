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

public class StudentHomeworkFragmentBackUP extends Fragment {
    private RecyclerView recyclerView;
    private List<AssignDate> assignDateList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_homework, container, false); //回傳父元件(linearLayout) 最尾要記得加false否則預設為true

        getActivity().setTitle(R.string.title_homework);

        findViews(view);

        //rv
        assignDateList = getAssignDateList();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new HomeworkAdapter(assignDateList, getActivity()));

        return view; //要改成回傳view
    }

    //rv假資料
    private List<AssignDate> getAssignDateList() {
        List<AssignDate> assignDateList = new ArrayList<>();

        Homework homework = new Homework("物理",false);
        Homework homework2 = new Homework("數學",false);
        Homework homework3 = new Homework("國文",true);
        Homework homework4 = new Homework("歷史",false);
        Homework homework5 = new Homework("作文",true);

        List<Homework> homeworkList = null;
        homeworkList.add(homework);
        homeworkList.add(homework2);
        homeworkList.add(homework3);
        homeworkList.add(homework4);
        homeworkList.add(homework5);

        List<Homework> homeworkList2 = null;
        homeworkList2.add(homework);

        List<Homework> homeworkList3 = null;
        homeworkList3.add(homework);
        homeworkList3.add(homework2);
        homeworkList3.add(homework5);


        assignDateList.add(new AssignDate(107, 4, 10,homeworkList));
        assignDateList.add(new AssignDate(107, 4, 6,homeworkList2));
        assignDateList.add(new AssignDate(107, 4, 3,homeworkList3));

        return assignDateList;
    }

    private void findViews(View view) {
        recyclerView = view.findViewById(R.id.rvHomework);

    }


    //rv
    private class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.HomeworkViewHolder> {
        private List<AssignDate> assignDateList;
        private Context context;

        public HomeworkAdapter(List<AssignDate> assignDateList, Context context) {
            this.assignDateList = assignDateList;
            this.context = context;
        }

        @NonNull
        @Override
        public HomeworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View itemView = layoutInflater.inflate(R.layout.cardview_homework, parent, false);


            return new StudentHomeworkFragmentBackUP.HomeworkAdapter.HomeworkViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull HomeworkViewHolder homeworkViewHolder, int position) {
            AssignDate assignDate = assignDateList.get(position);
            homeworkViewHolder.textView.setText(assignDate.showDate());
            homeworkViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Call Detail!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return assignDateList.size();
        }

        class HomeworkViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;


            HomeworkViewHolder(View itemView) {
                super(itemView);
                this.textView = itemView.findViewById(R.id.tvDate);

            }
        }

        //取得作業最多的那個日期(耗資源?)-> 取得最大作業數
        public int getMaxNumberOfHomework(List<AssignDate> assignDateList){
            int maxNumberOfHomework = 0;

            for (int i = 0; i <assignDateList.size(); i++){

                if (maxNumberOfHomework < assignDateList.get(i).getHomeworkList().size()){
                    maxNumberOfHomework = assignDateList.get(i).getHomeworkList().size();
                }

            }

            return maxNumberOfHomework;
        }


    }
}
