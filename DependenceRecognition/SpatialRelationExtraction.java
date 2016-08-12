package DependenceRecognition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import SpatialRelationship.Word;
import SpatialRelationship.SpatialRelationshipTriple;

/**
 * 空间关系抽取
 * @author Administrator
 *
 */
public class SpatialRelationExtraction {
	RecognizeHelper helper=new RecognizeHelper();//帮助函数
	
	
	
	/**
	 * 方位词识别
	 * @param allWords 一句话中所有的词
	 * @return 方位词列表
	 */
	public List<Word> DirectionNounRecognize(List<Word> allWords){
			List<Word> directionNouns = new ArrayList<Word>();
			for(int i=0;i<allWords.size();i++){
				if(allWords.get(i).getPos().equals("nd")){//判断是词性是否为方位词
					directionNouns.add(allWords.get(i));
				}
			}
			if(directionNouns.size()==0){
				System.out.println("本句没有识别到方位词");
			}
			return directionNouns;
		}

	/**
	 * 界标识别
	 * @param allWords 所有的词
	 * @param directionNoun 方位词
	 * @return 界标
	 */
	public Word LandmarkRecognize(List<Word> allWords,Word directionNoun){
		List<Word> landmark=new ArrayList<Word>();
		//遍历所有的词，找到父节点是方位词的那个词
		for(int i=0;i<allWords.size();i++){
			if(allWords.get(i).getParent().equals(directionNoun.getId())){
				landmark.add(allWords.get(i));
			}
		}
		//验证，依存关系为ATT
		for(int j=0;j<landmark.size();j++){
			if(!landmark.get(j).getRelate().equals("ATT")){
				landmark.remove(landmark.get(j));
			}
		}
		//模型库检测
		while(true){//如果模型库中有这个界标，那么确定界标，否则找到这个界标的直接依存对象，更新lamndmark，继续作判断。
			return landmark.get(0);
		}
	}

	/**
	 * 射体识别
	 * @param allWords 所有词
	 * @param directionNoun 方位词
	 * @return
	 */

	public Word trajectorRecognize(List<Word> allWords,Word directionNoun){
		RecognizeHelper helper=new RecognizeHelper();
		//List<Word> resultOfTrajector=new ArrayList<Word>();
		Word resultOfTrajector=new Word();
		//如果方位词不是主要的方位词，依存关系为ATT
		if(directionNoun.getRelate().equals("ATT")){
			for(int i=0;i<allWords.size();i++){
				if(allWords.get(i).getId().equals(directionNoun.getParent())){
					resultOfTrajector=allWords.get(i);
				}
			}
			//验证
			if(resultOfTrajector.getPos().contains("n")||resultOfTrajector.getPos().equals("r")){
				return resultOfTrajector;
			}
		}

		else{//父节点的依存关系为HED或者父节点的父节点的...父节点的依存关系为HED
			resultOfTrajector=directionNoun;
			while(!helper.getParentWord(allWords, resultOfTrajector).getRelate().equals("HED")){
				resultOfTrajector=helper.getParentWord(allWords, resultOfTrajector);
			}
			for(int i=0;i<allWords.size();i++){
				if(allWords.get(i).getParent().equals(resultOfTrajector.getParent())&&(allWords.get(i).getPos().contains("n")
						||allWords.get(i).getPos().equals("r"))&&!allWords.get(i).getId().equals(resultOfTrajector.getId())){
						resultOfTrajector=allWords.get(i);
						return resultOfTrajector;
				}
	 		}
		}
		return resultOfTrajector;
	}

	
	/**
	 * 获取空间关系的抽取结果，
	 * @return 空间关系三元组的List
	 * @throws IOException 
	 */
	public List<SpatialRelationshipTriple> getSentenceSpatialTriple(String sentence) throws IOException{
		
		//处理短句子，变为xml的格式，之后抽取信息，得到List<Word>的对象。
		CorpusPretreatment cp=new CorpusPretreatment();	
		List<Word> allDependences = cp.getWordProperty(sentence);
		List<SpatialRelationshipTriple> resultOfSpatialRelationshipExtract=new ArrayList<SpatialRelationshipTriple>();
		//获取方位词识别结果
		List<Word> directionNouns=DirectionNounRecognize(allDependences);
		//一般情况方位词的个数等于三元组的个数,还有一种情况是多个射体多个三元组，下面做处理
		for(int i=0;i<directionNouns.size();i++){
			SpatialRelationshipTriple temp=new SpatialRelationshipTriple();
			temp.setDirectionNoun(directionNouns.get(i));
			temp.setLandmark(LandmarkRecognize(allDependences, directionNouns.get(i)));
			temp.setTrajector(trajectorRecognize(allDependences, directionNouns.get(i)));
			resultOfSpatialRelationshipExtract.add(temp);
			//处理多个射体
			resultOfSpatialRelationshipExtract=helper.getAllTrajectorOfTriple(resultOfSpatialRelationshipExtract, temp, allDependences);
		}		
		return resultOfSpatialRelationshipExtract;
	}
}
