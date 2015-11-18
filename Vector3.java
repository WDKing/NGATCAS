/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nextgen;

public class Vector3
{
    public double x, y, z;

    public Vector3(double a, double b, double c)
    {
        x = a; y = b; z = c;
    }

    public Vector3 plus(Vector3 v)
    {
        return new Vector3(x + v.x, y + v.y, z + v.z);
    }

    public Vector3 minus(Vector3 v)
    {
        return new Vector3(x - v.x, y - v.y, z - v.z);
    }

    public Vector3 mult(double f) { return new Vector3(f * x, f * y, f * z); }

    public Vector3 cross(Vector3 v)
    {
        return new Vector3(y * v.z - v.y * z,
                           z * v.x - v.z * x,
                           x * v.y - v.x * y);
    }

    public double dot(Vector3 v)
    {
        return x * v.x + y * v.y + z * v.z;
    }

    public double length()
    {
        return (double)Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3 normal()
    {
        double length = length();
        return new Vector3(x / length, y / length, z / length);
    }

    public double angleBetween(Vector3 v)
    {
        return (double)Math.toDegrees(Math.acos(this.dot(v) / (this.length() * v.length())));
    }

}
