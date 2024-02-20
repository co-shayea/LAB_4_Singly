import java.util.Objects;

public class SinglyLinkedList<E> {
    private Node<E>head=null;
    private Node<E>tail=null;
    private int size=0;
    public SinglyLinkedList() {}
//    public int size(){
//        return  size;
//    }
    public  boolean isEmpty(){
        return  size==0;
    }
    public E first(){
        if (isEmpty())return  null;
        return head.getElement();
    }
    public  E last(){
        if (isEmpty())return null;
        return tail.getElement();
    }
    public void addFirst(E e){
        head=new Node<>(e,head);
        if (size==0)
            tail=head;
        size++;
    }
    public void addLast(E e){
        Node<E>  newest=new Node<>(e,null);
        if (size==0)
            head=newest;
        else
            tail.setNext(newest);
        tail=newest;
        size++;
    }
    public E removeFirst(){
        if (isEmpty())return null;
        E deleted= head.getElement();
        head=head.getNext();
        size--;
        if (size==0)
            tail=null;
        return deleted;
    }

// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>******<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//   Q1. develop an implementation of the equals method
//  in the context of the SinglyLinkedList class.
    @Override
    public boolean equals(Object o) {
//      إذا كان الاوبجكت هو نفسه this يرجع true
        if (this == o) return true;
//        اذا كان الكائن فارغا او ليس من نفس الكلاس يرجع false
        if (o == null || getClass() != o.getClass()) return false;

        SinglyLinkedList<E> thatO = (SinglyLinkedList<E>) o;
//        تحقق اذا كانت القائمتين فارغتين
        if (head == null && thatO.head == null){

            return true; // يعني انهما متساويتان
        }
//        تحقق اذا كانت اي قائمة فارغة والأخرى ليست كذلك
        if(head == null || thatO.head == null){
            return false; // الفائمتان غير متساويتان
        }
//        التكرار ومقارنة العناصر
        Node<E> current1 = head;
        Node<E> current2 = thatO.head;
        while(current1 != null && current2 != null){
            if(!current1.element.equals(current2.element)){
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }
//        تحقق إذا وصلت القائمتان الى النهاية في نفس الوقت
        return current1 == null && current2 == null;
//        return size == thatO.size && Objects.equals(head, thatO.head) && Objects.equals(tail, that.tail);
    }
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>******<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//    Q2.   Give an algorithm for finding the second-to-last node in a singly linked list
//    in which the last node is indicated by a null next reference.
    public Node<E> findSecondLastNode(Node<E> head) {
        if (head == null || head.next == null) {
            return null; // List is empty or has only one element
        }
        Node<E> hero = head;
        while (hero.next != null && hero.next.next != null) {
            hero = hero.next;
        }
        return hero;
    }
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>******<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//   Q3.  Give an implementation of the size( ) method for the SingularlyLinkedList class,
//       assuming that we did not maintain size as an instance variable
    public int size() {
//        عمل عداد بقيمة 0
        int counting = 0;
//        عمل نود مؤقته على رأس القائمة
        Node<E> currentElement = head;
//        طالما current ليست فارغة:
        while (currentElement != null) {
            counting++; //    قم بزيادة قيمة المتغير count.
            currentElement = currentElement.next; // حرك current إلى العقدة التالي
        }
// بعد التكرار، سيحتوي count على عدد العقد في القائمة.
        return counting;
    }
// تتكرر هذه الطريقة عبر القائمة المرتبطة مرة واحدة،
// حيث تحسب عدد العقد التي تم مواجهتها. إنها تتجنب استخدام متغير حالة إضافي لتخزين الحجم،
// مما يجعل الكود أكثر كفاءة في استخدام الذاكرة. ومع ذلك، من المهم ملاحظة أن هذا النهج يتطلب التكرار عبر القائمة بأكملها
// في كل مرة تحتاج فيها إلى معرفة الحجم، والذي قد يكون أقل كفاءة لفحوصات الحجم المتكررة مقارنة بالحفاظ على متغير حجم محدث.

// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>******<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//   Q4.  Implement a rotate( ) method in the SinglyLinkedList class,
// which has semantics equal to addLast(removeFirst( )),yet without creating any new node.

    public void rotate() {
        if (head == null || head.next == null) {
            return; // القائمة فارغة أو تحتوي على عقدة واحدة فقط
        }

        // ابحث عن العقدة الأخيرة
        Node<E> lastNode = head;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }

        // انقل العقدة الأولى إلى النهاية
        lastNode.next = head;
        head = head.next;
        lastNode.next.next = null; // افصل العقدة الأولى (الآن أصبحت الأخيرة)
    }
//     يتم نقل العقدة الأولى إلى النهاية، ويصبح باقي القائمة هو الجزء الأول،
//     مما يحقق نفس سلوك addLast(removeFirst()) بدون إنشاء عقد جديدة.
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>******<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//    Q5.   Describe an algorithm for concatenating two singly linked lists L and M,
//    into a single list L′ that contains all the nodes of L followed by all the nodes of M.
//
//  خوارزمية لدمج قائمتين مرتبطتين أحاديتين L و M في قائمة واحدة L′
//
//    تحقق من القوائم الفارغة:
//        إذا كانت كلتا القائمتين L و M فارغتين، فالقائمة الناتجة L′ أيضًا فارغة.
//        إذا كانت إحداهما فقط فارغة، فالنتيجة هي القائمة الأخرى غير الفارغة (L′ = L أو L′ = M).
//
//    ابحث عن العقدة الأخيرة في L:
//        تحرك مؤشرًا عبر L حتى يصل إلى العقدة الأخيرة التي تشير next שלה إلى null.
//
//    وصل L و M:
//        اضبط next للعقدة الأخيرة في L ليشير إلى رأس القائمة M.
//        الآن، القائمة L′ تتكون من جميع عقد L متبوعة بجميع عقد M.
//
//    إرجاع رأس القائمة L′ (وهو رأس القائمة L الأصلية):
//        لا يلزم إنشاء أي قائمة جديدة، حيث تم تعديل القائمة L الأصلية لتحتوي على جميع العقد المطلوبة.
//
//ملحوظة: هذه الخوارزمية تقوم بالتعديل على القائمة L الأصلية بدلاً من إنشاء قائمة جديدة. تأكد من أن هذا يتوافق مع متطلباتك الخاصة بالبرنامج.
//
//مثال:
//
//لنفترض أن L = {1, 2, 3} و M = {4, 5, 6}.
//
//    L ليست فارغة و M ليست فارغة.
//    العقدة الأخيرة في L هي العقدة التي تحتوي على القيمة 3.
//    next للعقدة الأخيرة في L يتم ضبطه ليشير إلى رأس القائمة M (العقدة التي تحتوي على القيمة 4).
//    القائمة الناتجة L′ تصبح {1, 2, 3, 4, 5, 6}.
//
//تعقيد الوقت: O(n + m)، حيث n و m هما عدد العقد في L و M على التوالي. يتطلب الأمر O(n) للعثور على العقدة الأخيرة في L و O(1) لربط القائمتين.
//
//تعقيد المساحة: O(1)، حيث لا يتم إنشاء أي مساحة إضافية بخلاف المتغيرات المؤقتة.

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>******<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//    Q6.  Describe in detail an algorithm for reversing
//    a singly linked list L using only a constant amount of additional space

//   خوارزمية لعكس قائمة مرتبطة مفردة باستخدام مساحة ثابتة إضافية
//
//إليك خوارزمية لعكس قائمة مرتبطة مفردة L باستخدام مساحة ثابتة إضافية:
//
//    ابدأ بثلاثة مؤشرات current, previous, و next، كلها تشير إلى null.
//    تحرك current إلى العقدة الأولى في القائمة L.
//    ادخل في حلقة حتى يصبح current فارغًا:
//        احفظ عقدة next التالية للعقدة الحالية current.next في متغير مؤقت next.
//        اجعل current.next يشير إلى العقدة السابقة previous.
//        حرك previous إلى العقدة الحالية previous = current.
//        حرك current إلى العقدة التالية المحفوظة current = next.
//    عند الخروج من الحلقة، سيكون previous يشير إلى العقدة الأخيرة (السابقة) في القائمة الأصلية L، وهي الآن العقدة الأولى في القائمة المقلوبة.
//    إعادة previous الذي يشير إلى رأس القائمة المقلوبة.
//
//مثال:
//
//لنفترض أن القائمة المرتبطة الأصلية L هي {1, 2, 3, 4}.
//
//    المؤشرات current, previous, و next كلها تشير إلى null.
//    current يتم تحريكه إلى العقدة الأولى (العقدة التي تحتوي على القيمة 1).
//    تدخل الحلقة:
//        next يحفظ العقدة التالية (العقدة التي تحتوي على القيمة 2).
//        current.next يتم ضبطه ليشير إلى previous (الذي حاليًا فارغ).
//        previous يتم تحريكه إلى current (الذي يحتوي على القيمة 1).
//        current يتم تحريكه إلى next المحفوظ (العقدة التي تحتوي على القيمة 2).
//    تتكرر الخطوات 3.a إلى 3.d حتى يصبح current فارغًا.
//    بعد الخروج من الحلقة، previous يشير إلى العقدة الأخيرة في القائمة المقلوبة (العقدة التي كانت تحتوي على القيمة 4).
//
//القائمة المقلوبة الآن هي {4, 3, 2, 1}.
//
//تعقيد الوقت: O(n)، حيث n هو عدد العقد في القائمة. نكرر خلال القائمة مرة واحدة.
//
//تعقيد المساحة: O(1)، حيث نستخدم فقط ثلاثة متغيرات ثابتة إضافية (current, previous, و next).
//
//ملاحظة: هذه الخوارزمية تعدل القائمة الأصلية L بدلاً من إنشاء قائمة جديدة. تأكد من أن هذا يتوافق مع متطلباتك الخاصة بالبرنامج.
    private  static  class Node<E>{
        E element;
        Node<E>next;
        public Node(E element){
            this.element =element;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node(E element, Node<E> next){
            this.element=element;
            this.next=next;

        }
//  من خلال تطبيق طريقة equals() هذه، يمكنك مقارنة كائنات SinglyLinkedList
//  بشكل فعال وتحديد ما إذا كانت تحتوي على نفس البيانات بالترتيب نفسه.

    }
}
