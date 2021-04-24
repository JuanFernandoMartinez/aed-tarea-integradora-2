package structs;

import java.util.HashSet;

public class BST<K extends Comparable<K>, V> implements BSTInterface<K, V> {

	protected Node<K, V> root;

	private HashSet<K> setKeys;

	protected int weight;

	public BST() {

		weight = 0;
		setKeys = new HashSet<K>();

	}

	public boolean add(K key, V value) {

		boolean r = addBase(key, value) != null;

		if (r) {
			setKeys.add(key);
		}

		return r;
	}

	protected Node<K, V> addBase(K key, V value) {

		Node<K, V> newNode = new Node<K, V>(key, value);

		if (root != null) {
			return addRecursive(key, value, root, newNode);
		} else {
			root = newNode;
			weight++;
			return root;
		}
	}

	private Node<K, V> addRecursive(K key, V value, Node<K, V> currentNode, Node<K, V> newNode) {

		if (key.compareTo(currentNode.getKey()) > 0) {

			Node<K, V> right = currentNode.getRight();

			if (right != null) {
				Node<K, V> addedNode = addRecursive(key, value, right, newNode);

				if (addedNode != null) {
					right.update();
				}

				return addedNode;
			}

			else {
				currentNode.setRight(newNode);
				newNode.setParent(currentNode);
				weight++;
				return newNode;
			}
		} else if (key.compareTo(currentNode.getKey()) < 0) {
			Node<K, V> left = currentNode.getLeft();

			if (left != null) {
				Node<K, V> addedNode = addRecursive(key, value, left, newNode);

				if (addedNode != null) {
					left.update();
				}

				return addedNode;
			}

			else {
				currentNode.setLeft(newNode);
				newNode.setParent(currentNode);
				weight++;
				return newNode;
			}
		} else {
			return null;
		}
	}

	@Override
	public boolean update(K key, V value) {

		Node<K, V> nodeToUpdate = searchNode(key);

		if (nodeToUpdate != null) {
			nodeToUpdate.setValue(value);

			return true;

		}

		else {

			return false;

		}

	}

	@Override
	public boolean remove(K key) {
		if (root != null) {

			boolean r = removeRecursive(key, root, null);

			if (r) {
				setKeys.remove(key);
			}

			return r;
		} else {
			return false;
		}
	}

	private boolean removeRecursive(K key, Node<K, V> currentNode, Node<K, V> parent) {
		if (currentNode != null) {
			if (key.compareTo(currentNode.getKey()) < 0) {
				return removeRecursive(key, currentNode.getLeft(), currentNode);
			} else if (key.compareTo(currentNode.getKey()) > 0) {
				return removeRecursive(key, currentNode.getRight(), currentNode);
			} else {
				if (currentNode.getLeft() == null && currentNode.getRight() == null) {

					if (currentNode == root) {
						root = null;
					} else {
						if (parent.getRight() != null && parent.getRight() == currentNode) {
							parent.setRight(null);
						} else {
							parent.setLeft(null);
						}
					}

				} else if (currentNode.getRight() == null) {

					if (currentNode == root) {
						root.getLeft().setParent(null);
						root = root.getLeft();
					} else {
						currentNode.getLeft().setParent(parent);

						if (parent.getRight() != null && parent.getRight() == currentNode)
							parent.setRight(currentNode.getLeft());

						else
							parent.setLeft(currentNode.getLeft());
					}

				} else if (currentNode.getLeft() == null) {

					if (currentNode == root) {
						root.getRight().setParent(null);
						root = root.getRight();
					} else {
						currentNode.getRight().setParent(parent);

						if (parent.getRight() != null && parent.getRight() == currentNode)
							parent.setRight(currentNode.getRight());
						else
							parent.setLeft(currentNode.getRight());
					}

				} else {
					Node<K, V> rightMin = getMin(currentNode.getRight());

					currentNode.setKey(rightMin.getKey());
					currentNode.setValue(rightMin.getValue());

					removeRecursive(rightMin.getKey(), rightMin, rightMin.getParent());

				}
				weight--;
				return true;
			}
		} else {
			return false;
		}
	}

	private Node<K, V> getMin(Node<K, V> node) {

		while (node.getLeft() != null) {

			node = node.getLeft();

		}

		return node;

	}

	@Override
	public V search(K key) {

		if (root != null) {

			return searchRecursive(key, root);

		}

		else {

			return null;

		}

	}

	private V searchRecursive(K key, Node<K, V> currentNode) {

		if (key.compareTo(currentNode.getKey()) == 0) {

			return currentNode.getValue();

		}

		else if (key.compareTo(currentNode.getKey()) > 0) {

			return searchRecursive(key, currentNode.getRight());

		}

		else {

			return searchRecursive(key, currentNode.getLeft());

		}

	}

	private Node<K, V> searchNode(K key) {
		if (root != null) {

			return searchNodeRecursive(key, root);

		}

		else {

			return null;

		}
	}

	private Node<K, V> searchNodeRecursive(K key, Node<K, V> currentNode) {

		if (key.compareTo(currentNode.getKey()) == 0) {

			return currentNode;

		}

		else if (key.compareTo(currentNode.getKey()) > 0) {

			return searchNodeRecursive(key, currentNode.getRight());

		}

		else {

			return searchNodeRecursive(key, currentNode.getLeft());

		}

	}

	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public boolean keyExists(K key) {

		return setKeys.contains(key);

	}

}