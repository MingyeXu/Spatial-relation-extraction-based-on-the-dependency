package SpatialRelationship;
/**
 * ¥ ”Ô¿‡
 * @author Administrator
 *
 */
public class Word {
	private String id;
	private String cont;
	private String pos;
	private String ne;
	private String parent;
	private String relate;
	private String semparent;
	private String semrelate;
	
	
	
	public Word() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Word(String id, String cont, String pos, String ne, String parent, String relate,
			String semparent, String semrelate) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.cont=cont;
		this.pos=pos;
		this.ne=ne;
		this.parent=parent;
		this.ne=ne;
		this.parent=parent;
		this.relate=relate;
		this.semparent=semparent;
		this.semrelate=semrelate;
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCont() {
		return cont;
	}
	
	public void setCont(String cont) {
		this.cont = cont;
	}
	
	public String getPos() {
		return pos;
	}
	
	public void setPos(String pos) {
		this.pos = pos;
	}
	
	public String getNe() {
		return ne;
	}
	
	public void setNe(String ne) {
		this.ne = ne;
	}
	
	public String getParent() {
		return parent;
	}
	
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getRelate() {
		return relate;
	}
	
	public void setRelate(String relate) {
		this.relate = relate;
	}
	
	public String getSemparent() {
		return semparent;
	}
	
	public void setSemparent(String semparent) {
		this.semparent = semparent;
	}
	
	public String getSemrelate() {
		return semrelate;
	}
	
	public void setSemrelate(String semrelate) {
		this.semrelate = semrelate;
	}

//	public String toString(){
//		return reln +"("+gov +","+dep+")";
//	}
}
