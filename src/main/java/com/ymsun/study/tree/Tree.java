package com.ymsun.study.tree;

import com.ymsun.study.entity.Person;

import java.util.Arrays;

/**
 * @author ymsun
 * @date 2020/8/13 11:03
 */
public class Tree {

    public static void main(String[] args) {
        BinaryTree<Person> tree = new BinaryTree<>();
        //数据的添加
        tree.add(new Person("A",80));
        tree.add(new Person("C",50));
        tree.add(new Person("F",60));
        tree.add(new Person("H",30));
        tree.add(new Person("G",90));
        tree.add(new Person("Y",10));
        tree.add(new Person("Z",55));
        tree.add(new Person("W",70));
        tree.add(new Person("X",85));
        tree.add(new Person("K",95));
        //数据按照中序进行排序
        System.out.println(Arrays.toString(tree.toArray()));
        tree.remove(new Person("H",30));
        System.out.println(Arrays.toString(tree.toArray()));
    }

}

/**
 * 实现二叉树的操作
 * @param <T>
 */
class BinaryTree<T extends Comparable<T>>{

    // ------------------------  以下是对二叉树的功能实现  ------------------------------
    private Node root;      //保存根节点
    private int count;      //保存数据的个数
    private Object[] returnData;    //返回的数据
    private int foot;       //脚标控制
    /**
     * 定义的树的结构体
     */
    private class Node{
        private Comparable<T> data;
        private Node parent;    //保存父节点
        private Node left;      //保存左子树
        private Node right;     //保存右子树
        public Node (Comparable<T> data){       //构造方法进行数据的存储
            this.data = data;
        }

        /**
         * 实现节点数据的适当位置的存储
         * @param newNode 新创建的新节点
         */
        public void addNode(Node newNode){
            if (newNode.data.compareTo((T)this.data) <= 0){  //新创建的节点比当前节点小，则应该放在左子树当中
                if(this.left == null){
                    this.left = newNode;        //没有左子树则新建左子树
                    newNode.parent = this;      //保存父节点
                }else {
                    this.left.addNode(newNode);     //继续向下判断
                }
            }else {     //比根节点的数据要大
                if(this.right == null){
                    this.right = newNode;   //没有右子树则新建右子树
                    newNode.parent = this;  //保存父节点
                }else {
                    this.right.addNode(newNode);     //继续向下判断
                }
            }
        }

        /**
         * 判断是否包含此节点
         * @param data
         * @return
         */
        public boolean containsNode(Comparable<T> data){
            if (data.compareTo((T)this.data) == 0){
                return true;
            }else if (data.compareTo((T)this.data) < 0){
                if (this.left != null){
                    return this.left.containsNode(data);
                }else {
                    return false;
                }
            }else if (data.compareTo((T)this.data) > 0){
                if (this.right != null){
                    return this.right.containsNode(data);
                }else {
                    return false;
                }
            }
            return false;
        }

        /**
         * 获取要删除的节点对象
         * @param data
         * @return
         */
        public Node getRemoveNode(Comparable<T> data){
            if (data.compareTo((T)this.data) == 0){
                return this;
            }else if (data.compareTo((T)this.data) < 0){
                if (this.left != null){
                    return this.left.getRemoveNode(data);
                }else {
                    return null;
                }
            }else if (data.compareTo((T)this.data) > 0){
                if (this.right != null){
                    return this.right.getRemoveNode(data);
                }else {
                    return null;
                }
            }
            return null;
        }

        /**
         * 实现所有数据的处理，按照中序遍历来完成
         */
        public void toArrayNode(){
            if (this.left != null){             //代表有左子树
                this.left.toArrayNode();        //进行递归调用，数据取出
            }
            BinaryTree.this.returnData[BinaryTree.this.foot ++] = this.data;
            if (this.right != null){
                this.right.toArrayNode();   //进行递归调用，数据取出
            }
        }
    }

    /**
     * 进行数据的保存
     * @param data:要保存的内容
     */
    public void add(Comparable<T> data){
        if(data == null){
            throw new NullPointerException("保存的数据不允许为空");
        }
        //赋值每次要添加的数据
        Node newNode = new Node(data);
        if (this.root == null){
            this.root = newNode;
        }else {     //需要为其保存一个合适的节点
            this.root.addNode(newNode);     //交给Node类进行负责处理
        }

        this.count ++;
    }

    public Object[] toArray(){
        if (this.count == 0){
            return null;
        }else {
            this.returnData = new Object[this.count];   //以保存的长度为数据长度
            this.foot = 0;
            this.root.toArrayNode();    //直接通过Node类负责
            return this.returnData;  //返回全部的数据
        }
    }

    /**
     * 执行数据的删除处理
     * @param data
     */
    public void remove(Comparable<T> data){
        if (this.root == null){
            return;
        }else {
            if (this.root.data.compareTo((T)data) == 0){
                Node moveNode = this.root.right;
                while (moveNode.left != null){
                    moveNode = moveNode.left;
                }
                moveNode.left = this.root.left;
                moveNode.right = this.root.right;
                moveNode.parent.left = null;
                this.root = moveNode;
            }else {
                Node removeNode = this.root.getRemoveNode(data);
                if (removeNode == null){
                    //情况一：没有任何子节点
                    if (removeNode.left == null || removeNode.right == null){
                        removeNode.parent.left = null;       //父节点直接断开为空
                        removeNode.parent.right = null;       //父节点直接断开为空
                        removeNode.parent = null;       //父节点直接断开为空
                    }else if(removeNode.left != null || removeNode.right == null){  //左边不为空
                        removeNode.parent.left = removeNode.left;
                        removeNode.left.parent = removeNode.parent;     //把删除节点的左孩子的父亲节点指向删除节点的父节点，那么删除节点就删除了
                    }else if(removeNode.left == null || removeNode.right != null){  //右边不为空
                        removeNode.left.left = removeNode.right;
                        removeNode.right.parent = removeNode.parent;
                    }else {     //两边节点都不为空
                        Node moveNode = removeNode.right;       //定义需要移动的节点
                        while (moveNode.left != null){          //循环找到是否还有左孩子节点
                            moveNode = moveNode.left;
                        }
                        removeNode.parent.left = moveNode;
                        moveNode.parent.left = null;
                        moveNode.parent = removeNode.parent;
                        moveNode.right = removeNode.right;
                        moveNode.left = removeNode.left;

                    }
                }
            }
            this.count --;
        }

    }

}
