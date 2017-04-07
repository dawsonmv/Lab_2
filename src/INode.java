/**
 * Created by dawsonvaldes on 4/5/17.
 */
public interface INode
{

    public INode next();
    public void set_next(INode n);
    public void link(INode a, INode b);
    public void unlink();

}
