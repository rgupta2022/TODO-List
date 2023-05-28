package com.example.todoapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TODORepository {

    public static TodoDao todoDao;
    private UserDao userDao;
    private LiveData<List<TODO>> todoList;

    public TODORepository(Application application)
    {
        TODODatabase todoDatabase = TODODatabase.getInstance(application);
        todoDao = todoDatabase.todoDao();
        todoList = todoDao.getAllData();
    }

    public void insertData(TODO todo){new InsertTask(todoDao).execute(todo);}
    public void updateData(TODO todo){new UpdateTask(todoDao).execute(todo);}
    public void deleteData(TODO todo){
        new DeleteTask(todoDao).execute(todo);
    }

    public void deleteAllData(){
        new DeleteAllTask().execute();
    }

    public LiveData<List<TODO>> getTodoList() {
        return todoList;
    }


    private static class InsertTask extends AsyncTask<TODO,Void,Void>{

        private TodoDao todoDao;

        public InsertTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(TODO... todos) {

            todoDao.insert(todos[0]);

            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<TODO,Void,Void>{

        private TodoDao todoDao;

        public DeleteTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(TODO... todos) {

            todoDao.delete(todos[0]);

            return null;
        }
    }
    private static class DeleteAllTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            if (TODORepository.todoDao != null) {
                TODORepository.todoDao.deleteAllTodo();

            }
            return null;

        }
    }

    private static class UpdateTask extends AsyncTask<TODO,Void,Void>{

        private TodoDao todoDao;

        public UpdateTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(TODO... todos) {

            todoDao.update(todos[0]);

            return null;
        }
    }
}
