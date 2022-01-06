from gpiozero import LED
led1 = LED(16)
led2 = LED(20)
led3 = LED(21)
led4 = LED(27)
led5 = LED(23)
led6 = LED(12)
isITBlinking = False
isItStoppedFirsttime = True
while(True):
    try:
        fh1 = open("shouldLedBlink.txt",'r', encoding = 'utf-8')
        if fh1 != None:
            data = fh1.read(1)
            #print(isITBlinking)
            if(data == "1" and isITBlinking == False):
                print(data)
                isITBlinking = True
                print(data)
                led1.off()
                led2.off()
                led3.off()
                led4.off()
                led5.off()
                led6.off()

                led1.blink()
                led2.blink()
                led3.blink()
                led4.blink()
                led5.blink()
                led6.blink()
                isItStoppedFirsttime = True
            else:
                fh2 = open("isNightModeON.txt",'r', encoding = 'utf-8')

                if data == "0" and isItStoppedFirsttime == True:
                    data2 = fh2.read(1)
                    isITBlinking = False
                    if data2 == "1":
                        led1.on()
                        led2.on()
                        led3.on()
                        led4.on()
                        led5.on()
                        led6.on()
                    else:
                        led1.off()
                        led2.off()
                        led3.off()
                        led4.off()
                        led5.off()
                        led6.off()
                    isItStoppedFirsttime = False
    except OSError as e:
        print("File error")