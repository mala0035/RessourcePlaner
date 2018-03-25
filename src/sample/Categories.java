package sample;

public class Categories {

    private boolean musicCheckbox=false;
    private boolean standardCheckbox = false;
    private boolean merchandiseCheckbox = false;
    private boolean servingCheckbox = false;
    private boolean othersCheckbox= false;

    //Constructor
    public Categories(boolean musicCheckbox,boolean standardCheckbox, boolean merchandiseCheckbox,boolean servingCheckbox,boolean othersCheckbox){
        this.merchandiseCheckbox= merchandiseCheckbox;
        this.musicCheckbox= musicCheckbox;
        this.othersCheckbox=othersCheckbox;
        this.servingCheckbox=servingCheckbox;
        this.standardCheckbox=standardCheckbox;
    }


    //getter
    public boolean getMusicCheckbox(){
        return musicCheckbox;
    }

    public boolean getStandardCheckbox(){
        return standardCheckbox;
    }

    public boolean getMerchandiseCheckbox(){
        return merchandiseCheckbox;
    }

    public boolean getServingCheckbox(){
        return servingCheckbox;
    }

    public boolean getOthersCheckbox(){
        return othersCheckbox;
    }




}
