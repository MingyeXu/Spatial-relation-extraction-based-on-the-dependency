package SpatialRelationship;
/**
 * 空间关系三元组类
 * @author Administrator
 *
 */
public class SpatialRelationshipTriple {
	private Word trajector;//射体
	private Word landmark;//界标
	private Word directionNoun;//方位词
	public SpatialRelationshipTriple() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SpatialRelationshipTriple(Word trajector, Word landmark,
			Word directionNoun) {
		super();
		this.trajector = trajector;
		this.landmark = landmark;
		this.directionNoun = directionNoun;
	}
	public Word getTrajector() {
		return trajector;
	}
	public void setTrajector(Word trajector) {
		this.trajector = trajector;
	}
	public Word getLandmark() {
		return landmark;
	}
	public void setLandmark(Word landmark) {
		this.landmark = landmark;
	}
	public Word getDirectionNoun() {
		return directionNoun;
	}
	public void setDirectionNoun(Word directionNoun) {
		this.directionNoun = directionNoun;
	}


}
