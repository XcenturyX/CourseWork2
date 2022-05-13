package com.example.coursework;

import javafx.scene.image.Image;

import java.util.Stack;

public class BufferImages {
    private Stack<Image> BufferImige;
    public BufferImages(){
        BufferImige=new Stack<>();
    }
    public Image ReturnImeginn(){
        return BufferImige.pop();
    }
    public void PutImeginn(Image image){
        BufferImige.push(image);
    }
    public boolean isNullImageStack(){
        if(BufferImige.size()!=1&&BufferImige.size()!=0) return true;
        else return false;
    }
}
