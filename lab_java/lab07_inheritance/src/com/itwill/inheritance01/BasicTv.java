package com.itwill.inheritance01;

// 클래스: 속성(필드) + 생성자 + 기능(메서드) => 데이터 타입.
public class BasicTv {
    public static final int MIN_CHANNEL = 0;
    public static final int MAX_CHANNEL = 2;
    public static final int MIN_VOLUME = 0;
    public static final int MAX_VOLUME = 2;
    
    // 필드
    private boolean powerOn;    // 전원이 켜져 있는 지(true), 꺼져 있는 지 (false)를 저장하는 변수.
    private int channel;        // 채널 번호
    private int volume;         // 음량을 저장.
    
    
    // 생성자
    public BasicTv() {}


    public BasicTv(boolean powerOn, int channel, int volume) {
        this.powerOn = powerOn;
        
        if (channel < 0) {
            this.channel = 0;
        } else if (channel > 2) {
            this.channel = 2;
        } else {
            this.channel = channel;
        }
        
        if (volume < 0) {
            this.volume = 0;
        } else if (volume > 2) {
            this.volume = 2;
        } else {
            this.volume = channel;
        }
        
    }

    
    // getter & setter
    // return type이 boolean이면 이름을 get으로 사용하거나 is로 사용한다.
    public boolean isPowerOn() {
        return powerOn;
    }


    public void setPowerOn(boolean powerOn) {
        this.powerOn = powerOn;
    }


    public int getChannel() {
        return channel;
    }


    public void setChannel(int channel) {
        this.channel = channel;
    }


    public int getVolume() {
        return volume;
    }


    public void setVolume(int volume) {
        this.volume = volume;
    }
    
    
    
    // 메서드 - TV의 기능들
    public boolean powerOnOff() {
        // TODO: 켜져 있으면(powerOn == true), 전원을 false로 바꾸고 
        // 꺼져 있으면(powerOn == false), 전원을 true로 바꾸고, 그 상태를 리턴.
        powerOn = !powerOn;
        if (powerOn) {
            System.out.println("TV ON");
        } else {
            System.out.println("TV OFF");
        }
        
        return powerOn;
    }
    
    // 2. 채널 값 1 증가
    public int channelUp() {
        if(powerOn) {            
            if (channel == MAX_CHANNEL) {   // 현재 채널값이 최댓값이면 최솟값으로 변경
                channel = MIN_CHANNEL;
            } else {
                channel++;      // 그 외의 경우는 한 채널 올림
            }
            
            System.out.println("채널: " + channel);
        }
        
        return channel;
    }
    
    // 3. 채널 값 1 감소
    public int channelDown() {
        if (powerOn) {
            if (channel == MIN_CHANNEL) {       // 현재 채널값이 최솟값이면 최댓값으로 변경
                channel = MAX_CHANNEL;
            } else {
                channel--;      // 그 외의 경우는 한 채널 내림
            }
            
            System.out.println("채널: " + channel);
        }
        return channel;
    }
    
    // 4. 음량 크기 1 증가
    public int volumeUp() {
        if (powerOn) {
            if (volume != MAX_VOLUME) {
                volume++;
            }
            
            System.out.println("음량: " + volume);
        }
        return volume;
    }
    
    // 5. 음량 크기 1 감소
    public int volumeDown() {
        if (powerOn) {
            if (volume != MIN_VOLUME) {
                volume--;
            }
            
            System.out.println("음량 " + volume);
        }
        return volume;
    }
    
    public String toString() {
        return "BasicTv(powerOn: " + this.powerOn + ", channel: " + this.channel + ", volume: " + this.volume + ")";
    }
    
    
}
