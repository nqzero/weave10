name="Win.class"
cargs="-g"
pargs="-v -p -c -s -l"


cat src/main/java/Win.java | sed "s/fiber/()/" > Win.java


mkdir -p t1 t2 w1 w2
rm t?/*.class w?/*.class

         $java8/bin/javac $cargs -cp $cp1 -d t1 Win.java
         $java8/bin/javac $cargs -cp $cp2 -d t2 src/main/java/Win.java

         $java10/bin/javap $pargs t1/$name > q1
         $java10/bin/javap $pargs t2/$name > q2

         $java8/bin/java -cp t1:$cp1 kilim.tools.Weaver -d w1 t1
         $java10/bin/java -cp t2:$cp2 kilim.tools.Weaver -d w2 t2

         $java10/bin/javap $pargs w1/$name > y1
         $java10/bin/javap $pargs w2/$name > y2


