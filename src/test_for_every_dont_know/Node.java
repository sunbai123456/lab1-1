package test_for_every_dont_know;
/**.
* javadoc
*/
public class Node {
    /**.
    * javadoc
    */
    private static final int NODE_LENGTH = 50; //最大节点数量
    /**.
     * javadoc
     */
    //private boolean visitflag; //访问标志
    private Node[] next;  //指针域
    /**.
     * javadoc
     */
    private String[] weightparent;
    /**.
     * javadoc
     */
    private String data;
    //data 字符串数据//weightparent父节点信息对应weightnum
    /**.
     * javadoc
     */
    private int number, visitcount, selfvisitcount;
    /**.
     * javadoc
     */
    private int[] weightnum;
    /**.
     * javadoc
     */
    private boolean visitflag;
    //weightnum来自父指针的次数,即权重对应weightparent
    //number 子指针的个数
    //visitcount节点被访问的个数
    //selfvisitcount自身被访问的次数记录，用于修改visitflag
    /**.
     * javadoc
     * @param str str
     */
    public Node(final String str) {
        this.setData(str);
        setNumber(0);
        setWeightnum(new int[getNodeLength()]);
        setWeightparent(new String[getNodeLength()]);
        setNext(new Node[getNodeLength()]);
        //@SuppressWarnings("unused")
        //boolean visitflag = false;
        setVisitcount(0);
        setSelfvisitcount(0);
        for (int i = 0; i < getNodeLength(); i++) {
            getNext()[i] = null;
            getWeightnum()[i] = 0;
            getWeightparent()[i] = "";
            }
        }
    /**.
     * javadoc
     * @return a Str
     */
    public String getData() {
        return data;
    }
    /**.
     * javadoc
     * @param data data
     */
    public void setData(final String data) {
        this.data = data;
    }
    /**.
     * javadoc
     */
    public String[] getWeightparent() {
        return weightparent;
    }
    /**.
     * javadoc
     */
    public void setWeightparent(String[] weightparent) {
        this.weightparent = weightparent;
    }
    /**.
     * javadoc
     */
    public int[] getWeightnum() {
        return weightnum;
    }
    /**.
     * javadoc
     */
    public void setWeightnum(int[] weightnum) {
        this.weightnum = weightnum;
    }
    /**.
     * javadoc
     */
    public Node[] getNext() {
        return next;
    }
    /**.
     * javadoc
     */
    public void setNext(final Node[] next) {
        this.next = next;
    }
    /**.
     * javadoc
     */
    public int getNumber() {
        return number;
    }
    /**.
     * javadoc
     */
    public void setNumber(int number) {
        this.number = number;
    }
    /**.
     * javadoc
     */
    public static int getNodeLength() {
        return NODE_LENGTH;
    }
    /**.
     * javadoc
     */
    public boolean isVisitflag() {
        return visitflag;
    }
    /**.
     * javadoc
     */
    public void setVisitflag(boolean visitflag) {
        this.visitflag = visitflag;
    }
    /**.
     * javadoc
     */
    public int getVisitcount() {
        return visitcount;
    }
    /**.
     * javadoc
     */
    public void setVisitcount(int visitcount) {
        this.visitcount = visitcount;
    }
    /**.
     * javadoc
     */
    public int getSelfvisitcount() {
        return selfvisitcount;
    }
    /**.
     * javadoc
     */
    public void setSelfvisitcount(int selfvisitcount) {
        this.selfvisitcount = selfvisitcount;
    }
    }
