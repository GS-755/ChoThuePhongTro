package edu.nhom01.chothuetro.models.interfaces;

public interface IApiData {
    int getItemCount();
    void fetchData();
    void fetchData(int id);
    void fetchData(String id);
}
