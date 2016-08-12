package DependenceRecognition;

import java.io.IOException;
import java.util.List;

import SpatialRelationship.SpatialRelationshipTriple;

public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SpatialRelationExtraction sre=new SpatialRelationExtraction();
		List<SpatialRelationshipTriple> result=(List<SpatialRelationshipTriple>)sre.getSentenceSpatialTriple("小明和奥巴马在屋子后面的桥上钓鱼。");
		System.out.println("空间三元组个数为："+result.size());

		for(int i=0;i<result.size();i++){
			try{
				System.out.println("射体: "+result.get(i).getTrajector().getCont()+" 界标："+result.get(i).getLandmark().getCont()+" 方位词： "+result.get(i).getDirectionNoun().getCont());
			}
			catch(NullPointerException e){
				System.out.println("三元组提取失败");
			}
		}
		
		
	}

}
