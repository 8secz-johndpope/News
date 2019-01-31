package com.heaven.model.ifilm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heaven on 2017/2/8.
 */

public class FilmPreferSubmit {
    private ArrayList<FilmCategory> filmStyle;
    private ArrayList<FilmCategory> musicStyle;
    private List<UserMaterialDTO> filmOrder;


    public ArrayList<FilmCategory> getFilmStyle() {
        return filmStyle;
    }

    public void setFilmStyle(ArrayList<FilmCategory> filmStyle) {
        this.filmStyle = filmStyle;
    }

    public ArrayList<FilmCategory> getMusicStyle() {
        return musicStyle;
    }

    public void setMusicStyle(ArrayList<FilmCategory> musicStyle) {
        this.musicStyle = musicStyle;
    }

    public List<UserMaterialDTO> getFilmOrder() {
        return filmOrder;
    }

    public void setFilmOrder(List<UserMaterialDTO> filmOrder) {
        this.filmOrder = filmOrder;
    }
}
