package ratings;

import ratings.datastructures.Comparator;
import ratings.datastructures.LinkedListNode;
import ratings.datastructures.BinaryTreeNode;

public class Playlist {
    private Comparator<Song> compare;
    private BinaryTreeNode<Song> binaryTreeNode;

    public Playlist(Comparator<Song> compare) {
        this.compare = compare;
    }

    public void addSong(Song song) {
        if (binaryTreeNode == null) {
            binaryTreeNode = new BinaryTreeNode<>(song, null, null);
        } else {
            insertValue(binaryTreeNode, song);
        }
    }

    private void insertValue(BinaryTreeNode<Song> node, Song value) {
        if (compare.compare(value, node.getValue())) {
            if (node.getLeft() == null) {
                node.setLeft(new BinaryTreeNode<>(value, null, null));
            } else {
                insertValue(node.getLeft(), value);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new BinaryTreeNode<>(value, null, null));
            } else {
                insertValue(node.getRight(), value);
            }
        }
    }

    public BinaryTreeNode<Song> getSongTree() {
        return binaryTreeNode;
    }

    private LinkedListNode<Song> sortedLinkedList;

    public LinkedListNode<Song> getSongList(BinaryTreeNode<Song> node) {
        sortedLinkedList = null;
        sortSongList(node);
        return sortedLinkedList;
    }

    private void sortSongList(BinaryTreeNode<Song> node) {
        if (node != null) {
            sortSongList(node.getLeft());
            if (sortedLinkedList == null) {
                sortedLinkedList = new LinkedListNode<>(node.getValue(), null);
            } else {
                sortedLinkedList.append(node.getValue());
            }
            sortSongList(node.getRight());
        }
    }

    public LinkedListNode<Song> getSongList() {
        return getSongList(getSongTree());
    }

}
