package com.example.m82;

public class FortuneCookie {
    private String fortune;
    private int[] luckyNumbers;
    private String word;

    public FortuneCookie(String fortune, int[] luckyNumbers, String word){
        this.fortune = fortune;
        this.luckyNumbers = luckyNumbers;
        this.word = word;
    }
    public String getFortune(){
        return fortune;
    }
    public void setFortune(String fortune){
        this.fortune = fortune;
    }
    public int[] getLuckyNumbers(){
        return luckyNumbers;
    }
    public void setLuckyNumbers(int [] luckyNumbers){
        this.luckyNumbers = luckyNumbers;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fortune: ").append(fortune).append("\n");
        sb.append("Lucky Numbers: ");
        for (int number : luckyNumbers) {
            sb.append(number).append("  ");
        }
        sb.append("\n");
        sb.append("Word of the Day: ").append(word);
        return sb.toString();
    }
}
