name="Win.class"
cargs="-g"
pargs="-v -p -c -s -l"

         $java8/bin/javac $cargs -cp $cp -d t8 src/main/java/*.java
         $java9/bin/javac $cargs -cp $cp -d t9 src/main/java/*.java
         $java10/bin/javac $cargs -cp $cp -d t0 src/main/java/*.java

         $java10/bin/javap $pargs t8/$name > q8
         $java10/bin/javap $pargs t9/$name > q9
         $java10/bin/javap $pargs t0/$name > q0

         $java10/bin/java -cp t0:$cp kilim.tools.Weaver -d w0 t0
         $java8/bin/java -cp t8:$cp kilim.tools.Weaver -d w8 t8
         $java9/bin/java -cp t9:$cp kilim.tools.Weaver -d w9 t9


         $java10/bin/javap $pargs w8/$name > y8
         $java10/bin/javap $pargs w9/$name > y9
         $java10/bin/javap $pargs w0/$name > y0


	 return
	 
	 
         $java10/bin/javap $pargs t0/Cruel.class > u0
         $java9/bin/javap $pargs t9/Cruel.class > u9
         $java8/bin/javap $pargs t8/Cruel.class > u8

         $java10/bin/javap $pargs t8/Cruel.class > p8
         $java10/bin/javap $pargs t9/Cruel.class > p9
         $java10/bin/javap $pargs t0/Cruel.class > p0

         $java10/bin/javap $pargs w8/Cruel.class > x8
         $java10/bin/javap $pargs w9/Cruel.class > x9
         $java10/bin/javap $pargs w0/Cruel.class > x0


	 
