package ru.itlab.todolist;

import android.app.Application;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivityNewModel extends AndroidViewModel {
    private EditText noteDescription;
    private RadioGroup radioGroup;
    private Button addNoteButton;
    private NotesDatabase notesDatabase;
    private MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>(0);
    private CompositeDisposable compositeDisposable;

    public MainActivityNewModel(Application application){
        super(application);
        notesDatabase = NotesDatabase.getInstance(application);
        compositeDisposable = new CompositeDisposable();
    }
    protected void onCleared(){
        super.onCleared();
        compositeDisposable.dispose();
    }
    public LiveData<List<Note>> getNotes(){
        return notesDatabase.notesDAO().getNotes();
    }
    public void remove(Note note){
        Disposable disposable = notesDatabase.notesDAO().remove(note.id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
        compositeDisposable.add(disposable);
    }

    public LiveData<Integer> getTest(){
        return mutableLiveData;
    }
}

