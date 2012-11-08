all:
	javac -classpath lib/squintV2.13.jar TSPAnimated/*.java

clean:
	rm TSPAnimated/*.class
